package eu.cokeman.cycleareastats.out.persistence.jpa.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanWrapperImpl;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public abstract class BaseJpaEntity {

    @CreationTimestamp(source = SourceType.DB)
    private Instant createTime;
    @UpdateTimestamp(source = SourceType.DB)
    private Instant updateTime;

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapperImpl src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
            boolean isEntity = Arrays.stream(pd.getPropertyType().getAnnotations()).anyMatch(a->a.toString().contains("jakarta.persistence.Entity"));
            if(isEntity){
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
