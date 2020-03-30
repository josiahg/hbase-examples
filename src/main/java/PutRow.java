import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


public class PutRow {
    private static final TableName TABLE_NAME = TableName.valueOf("test_table_example");
    private static final byte[] CF_NAME = Bytes.toBytes("test_cf");
    private static final byte[] QUALIFIER = Bytes.toBytes("test_column");
    private static final byte[] ROW_ID = Bytes.toBytes("row01");

    public static void putRow(final Table table) throws IOException {
       table.put(new Put(ROW_ID).addColumn(CF_NAME,QUALIFIER, Bytes.toBytes("Some data")));
    }

    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();

        try (Connection connection = ConnectionFactory.createConnection(config); Table table = connection.getTable(TABLE_NAME)) {
            putRow(table);
        }
    }
}