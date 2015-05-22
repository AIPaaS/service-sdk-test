package test.com.ai.paas.ipaas.dbs.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.ai.paas.ipaas.dbs.sql.ParsedSqlContext;
import com.ai.paas.ipaas.dbs.sql.SqlParser;
import com.ai.paas.ipaas.dbs.sql.Table;
import com.ai.paas.ipaas.dbs.sql.parser.SQLLexer;
import com.ai.paas.ipaas.dbs.sql.parser.SQLParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.junit.Assert.assertEquals;

public class SqlParserTest {

    @org.junit.Test
    public void testFile() throws IOException {
        File sqlPath = new File(this.getClass().getResource("/com/db/test/sql").getFile());

        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        for (File sqlFile : sqlPath.listFiles()) {
            String parsed = gson.toJson(SqlParser.parseSql(new String(Files.readAllBytes(sqlFile.toPath())), true));
            File parsedFile = new File(this.getClass().getResource("/com/db/test/parsedResult/" + sqlFile.getName().substring(0, sqlFile.getName().length() - 4) + ".json").getFile());
            assertEquals(new String(Files.readAllBytes(parsedFile.toPath())), parsed);
        }
    }


}
