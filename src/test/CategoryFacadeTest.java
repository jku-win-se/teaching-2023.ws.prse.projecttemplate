import com.example.fahrtenbuch.business.DatabaseConnection;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CategoryFacadeTest {
    private DatabaseConnection dataBaseConnection;
    private Connection conn;

    public CategoryFacadeTestFacadeTest() {
        dataBaseConnection = new DatabaseConnection();
        conn = dataBaseConnection.getConnection();
    }

    @Test
    public void testPersistAndRetrieveCategory() throws SQLException {

        Category testCategory = new Category("TestCategory");
        categoryFacade.persistCategory(testCategory);

        Category retrievedCategory = categoryFacade.getCategoryById(testCategory.getCategory_id());

        assertNotNull(retrievedCategory);
        assertEquals(testCategory.getCategory_id(), retrievedCategory.getCategory_id());
        assertEquals(testCategory.getName(), retrievedCategory.getName());
    }

    @Test
    public void testGetAllCategories() {

        categoryFacade.persistCategory(new Category("TestCategory1"));
        categoryFacade.persistCategory(new Category("TestCategory2"));

        List<Category> categories = categoryFacade.getAllCategories();

        assertEquals(2, categories.size());
    }

    @Test
    public void testDeleteCategory() throws SQLException {

        Category testCategory = new Category("TestCategory");
        categoryFacade.persistCategory(testCategory);

        Category retrievedCategory = categoryFacade.getCategoryById(testCategory.getCategory_id());

        categoryFacade.deleteCatgoryById(retrievedCategory.getCategory_id());

        Category deletedCategory = categoryFacade.getCategoryById(retrievedCategory.getCategory_id());
        assertNull(deletedCategory);
    }