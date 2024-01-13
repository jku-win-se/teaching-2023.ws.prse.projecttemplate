package at.jku.se.prse.team3;

import com.dropbox.core.*;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CommitInfo;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.files.WriteMode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CloudBackup {

    private static final String CLIENT_IDENTIFIER = "wk400vxjbjtryt0";

    public static void uploadDB(String localPath, String cloudPath, String accessToken) {
        try (InputStream in = new FileInputStream(localPath)) {
            DbxRequestConfig config = DbxRequestConfig.newBuilder(CLIENT_IDENTIFIER).build();
            DbxClientV2 client = new DbxClientV2(config, accessToken);

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
