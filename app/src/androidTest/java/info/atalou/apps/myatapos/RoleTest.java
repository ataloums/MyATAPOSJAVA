package info.atalou.apps.myatapos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import info.atalou.apps.myatapos.database.AppDatabase;
import info.atalou.apps.myatapos.database.dao.RoleDao;
import info.atalou.apps.myatapos.database.entity.RoleEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class RoleTest {

    AppDatabase db;
    RoleDao store;

    @Before
    public void setUp(){
        db = AppDatabase.create(InstrumentationRegistry.getTargetContext(),true);
        store = db.roleDao();
    }

    @After
    public void tearDown(){
        db.close();
    }

    @Test
    public void basic(){
        assertEquals(0, store.selectAll().size());
        final RoleEntity first = new RoleEntity("Admin", new Date());
        assertNotNull(first.getId());
        store.insert(first);

        assertRole(store,first);
        final RoleEntity updated = new RoleEntity(first.getId(), " Manage", new Date());
        store.update(updated);

        assertRole(store, updated);

        store.delete(updated);
        assertEquals(0, store.selectAll().size());
    }

    private void assertRole(RoleDao store, RoleEntity first) {
        List<RoleEntity> results = store.selectAll();

        assertNotNull(results);
        assertEquals(1, results.size());
        assertTrue(areIdentical(first, results.get(0)));

        RoleEntity result = store.getRoleById(first.getId());

        assertNotNull(results);
        assertTrue(areIdentical(first, result));

    }

    private boolean areIdentical(RoleEntity first, RoleEntity second) {
        return (first.getName().equals(second.getName()) &&
                first.getId() == second.getId()
        );
    }
}
