package at.jku.se.prse.team3;

import com.dropbox.core.*;
import com.dropbox.core.v2.files.CommitInfo;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class CloudBackupTest {

    private static final String TEST_LOCAL_PATH = "testfile.txt";
    private static final String TEST_CLOUD_PATH = "/test/testfile.txt";
    private static final String TEST_ACCESS_TOKEN = "sl.Btq-q-GyvPeGuSM_Iis7lmxMAFb_kcsitoU0rBfZOJ3SYmWw2Yssueo3djYst3FeP1C1VxiCF0OM4dbPkkRnkaW9jp_jcBZ-1i_WxlOQgnAUpBJ7dBH1fyIqWDop6XvyexoCDUvMDrCWIoo";

    @BeforeAll
    static void setup() {
        try {
            File testFile = new File(TEST_LOCAL_PATH);
            if (!testFile.exists() && testFile.createNewFile()) {
                System.out.println("Test file created successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// Only test this if a current token is available !!!
    /*
    @Test
    void testUploadDB_WithValidInputs_ShouldUploadFile() {
        CloudBackup cloudBackup = new CloudBackup();
        assertDoesNotThrow(() -> cloudBackup.uploadDB(TEST_LOCAL_PATH, TEST_CLOUD_PATH, TEST_ACCESS_TOKEN));
    }
    */

    @Test
    void testUploadDB_WithInvalidAccessToken_ShouldThrowRuntimeException() {
        CloudBackup cloudBackup = new CloudBackup();
        String invalidAccessToken = "invalid-access-token";
        assertThrows(RuntimeException.class, () -> cloudBackup.uploadDB(TEST_LOCAL_PATH, TEST_CLOUD_PATH, invalidAccessToken));
    }
}
