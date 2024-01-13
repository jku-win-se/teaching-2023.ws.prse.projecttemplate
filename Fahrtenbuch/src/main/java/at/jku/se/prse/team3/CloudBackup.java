package at.jku.se.prse.team3;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CommitInfo;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.files.UploadUploader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CloudBackup {

    //Link to view Backup --> https://www.dropbox.com/scl/fo/3qpbm5kf5rfv9r7jh21k3/h?rlkey=litwwve441xfj954njoforq6n&dl=0

    private static final String ACCESS_TOKEN = "sl.BtmNnSH9o9nmtS52tnwbNHRON-XUiMl8Q3SRF-iIsMhuYtJ6QY9ScN40WnTgwjzjNcaHKGsznCdrKRX1a9MQ1OgG7gTKIjxN5k6PxgKhS5s4dP3CdDHlGXzUG6sbqSiVt7MeZiE2CgkasEU";
    private static final String CLIENT_IDENTIFIER = "wk400vxjbjtryt0";

    public static void uploadDB(String localPath, String cloudPath){
        try (InputStream in = new FileInputStream(localPath)) {
            DbxRequestConfig config = DbxRequestConfig.newBuilder(CLIENT_IDENTIFIER).build();
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

            CommitInfo commitInfo = CommitInfo.newBuilder(cloudPath)
                    .withMode(WriteMode.OVERWRITE)
                    .build();

            FileMetadata metadata = client.files().uploadBuilder(cloudPath)
                    .withMode(WriteMode.OVERWRITE)
                    .uploadAndFinish(in);

            System.out.println("File uploaded to Dropbox: " + metadata.getName());
        } catch (IOException | UploadErrorException e) {
            e.printStackTrace();
        } catch (DbxException e) {
            throw new RuntimeException(e);
        }

    }
}
