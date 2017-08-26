
package com.bugbusters.lajarus.repository;

import com.bugbusters.lajarus.entity.ItemEntity;
/*import static javassist.CtMethod.ConstParameter.string;*/
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>{
     @Query( "SELECT q FROM ItemEntity q WHERE q.id = :id" )
    public ItemEntity getItemById( @Param("id") long id );
     @Query( "SELECT q FROM ItemEntity q WHERE q.type = :type" )
    public ItemEntity getItemByType( @Param("type") String type );
    
    
    @Transactional
    @Modifying
    @Query( "DELETE from ItemEntity q WHERE q.id = :id" )
    public void deleteItem( @Param("id") long id );
    
    @Query("SELECT i FROM ItemEntity i WHERE i.name = :name") 
    public ItemEntity isThereItemByName( @Param("name") String name );
    
    
    
}
