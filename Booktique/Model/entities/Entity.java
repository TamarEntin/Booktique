package entities;

import java.io.Serializable;
import java.util.UUID;

public abstract class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    protected static String id = UUID.randomUUID().toString();

}
