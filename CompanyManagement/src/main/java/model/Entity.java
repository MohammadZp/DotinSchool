package model;

import org.hibernate.annotations.OptimisticLock;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;

import javax.persistence.*;

@MappedSuperclass
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    @Audited
    public Long id;
    @Column(name = "c_version")
    @Version
    public Integer version;
    @Audited
    @Column(name = "c_modificationDate")
    public String modificationDate;
    @Audited
    @Column(name = "c_creationDate")
    public String creationDate;
    @Column(name = "c_active")
    public boolean active;
    @Column(name = "c_enable")
    public boolean enable;

    public Entity() {
    }

    public boolean checkEquality(Entity entity) {
        return entity.equals(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
