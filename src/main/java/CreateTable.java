import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;


public class CreateTable {
    private static final TableName TABLE_NAME = TableName.valueOf("test_table_example");
    private static final String CF_NAME = "test_cf";

    public static void createTable(final Admin admin) throws IOException {
        if(!admin.tableExists(TABLE_NAME)) {
            System.out.println("Creating table " + TABLE_NAME.getNameAsString() + " ...");
            TableDescriptor desc = TableDescriptorBuilder.newBuilder(TABLE_NAME)
                    .setColumnFamily(ColumnFamilyDescriptorBuilder.of(CF_NAME))
                    .build();
            admin.createTable(desc);
        }
    }

    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();

        try (Connection connection = ConnectionFactory.createConnection(config); Admin admin = connection.getAdmin()) {
            createTable(admin);
        }
    }
}