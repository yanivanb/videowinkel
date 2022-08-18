package be.vdab.videowinkel.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Set<Long> ids = new LinkedHashSet<>();
    public void voegToe(long id) {
        ids.add(id);
    }

    public void verwijderUit(Long[] id){
        ids.removeAll(Arrays.stream(id).toList());
    }
    /*
    public void verwijderUit(Set<Long> id){
        ids.removeAll(id);
    }*/

    public Set<Long> getIds() {
        return ids;
    }
}
