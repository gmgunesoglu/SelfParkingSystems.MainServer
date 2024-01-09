package com.SelfParkingSystems.MainServer.security;

import com.SelfParkingSystems.MainServer.dto.JwtDto;
import com.SelfParkingSystems.MainServer.entity.Authority;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

@Data
public class SessionControl {
    private TokenQueue queue=new TokenQueue();  //çift yönlü dairesel sıra
    private HashMap<String, Authentication> jwtMap = new HashMap<>();
    private Timer timer = new Timer();  // farklı bir thread için...
    @Value("${jwt.TOKEN_DURATION}")
    private int tokenDuration;
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            killDeadTokens();
            System.out.println("[+] Olu tokenler kontrol edildi.");
        }
    };

    public SessionControl() {
        timer.schedule(task, 10000, 10000);
    }

    public boolean sessionIsValid(String jwt){
        return jwtMap.containsKey(jwt);
    }

    public void removeSession(String jwt){
        jwtMap.remove(jwt);
        System.out.println("[+] Oturum sonlandı:" + jwt);
    }

    public void removeSession(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        removeSession(token);
    }

    private void addToQueue(String token,String userName){
        if(queue.getData()==null){
            //ilk token
            queue.setData(token);
            queue.setUserName(userName);
            queue.setInitialDate(new Date(System.currentTimeMillis()));
            queue.next=queue;
            queue.prev=queue;
        }else if(queue.prev==queue){
            //tek token var
            TokenQueue iter=new TokenQueue(token,userName,new Date(System.currentTimeMillis()));
            queue.next=iter;
            queue.prev=iter;
            iter.prev=queue;
            iter.next=queue;
        }else{
            //en az 2 token var
            TokenQueue iter=new TokenQueue(token,userName,new Date(System.currentTimeMillis()));
            queue.prev.next=iter;
            iter.prev=queue.prev;
            queue.prev=iter;
            iter.next=queue;
        }
    }

    private void removeFromQueue(){
        if(queue.getData()!=null){
            // 0 token degil...
            if(queue.next==queue){
                // 1 token...
                queue.setInitialDate(null);
                queue.setData(null);
                queue.setUserName(null);
                queue.next=null;
                queue.prev=null;
            }else{
                // 1 den fazla token...
                TokenQueue iter=queue.next;
                queue.prev.next=iter;
                iter.prev=queue.prev;
                queue=iter;
            }
        }
    }

    private void killDeadTokens(){
        Date killTime = new Date(System.currentTimeMillis()-tokenDuration);
        if(queue.getInitialDate()!=null && queue.getInitialDate().before(killTime)){
            //kuyrugun basını kontrol et burdan silinen olursa hashmap den de sil
            removeSession(queue.getData());
            removeFromQueue();
            killDeadTokens();   //bir sonrakini de kontrol et
        }
    }

    //silinecek
    public List<String> listQueue(){
        List<String> listQueue = new ArrayList<>();
        if(queue.getData()!=null){
            TokenQueue iter = queue;
            listQueue.add(queue.getData());
            while(iter.next!=queue){
                iter=iter.next;
                listQueue.add(iter.getData());
            }
        }
        return listQueue;
    }

    public void addSession(String userName, Authority authority, String jwt) {
        SimpleGrantedAuthority personAuth = new SimpleGrantedAuthority(authority.toString());
        Authentication auth = new UsernamePasswordAuthenticationToken(userName, null, Collections.singleton(personAuth));
        jwtMap.put(jwt, auth);
        addToQueue(jwt, userName);
    }

    public Authentication getAuth(String jwt) {
        return jwtMap.get(jwt);
    }
}
