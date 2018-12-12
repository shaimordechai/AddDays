import java.text.SimpleDateFormat;

public class DateUtilities {
    private static SimpleDateFormat ddMMyyFormat = new SimpleDateFormat("ddMMyy");
    private static SimpleDateFormat ddMMyyyyFormat = new SimpleDateFormat("ddMMyyyy");
    private static SimpleDateFormat ddMMyySlashFormat = new SimpleDateFormat("dd/MM/yy");
    private static SimpleDateFormat ddMMyyyySlashFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat ddMMyyPointFormat = new SimpleDateFormat("dd.MM.yy");
    private static SimpleDateFormat ddMMyyyyPointFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static SimpleDateFormat getDdMMyyFormat() {
        return ddMMyyFormat;
    }

    public static void setDdMMyyFormat(SimpleDateFormat ddMMyyFormat) {
        DateUtilities.ddMMyyFormat = ddMMyyFormat;
    }

    public static SimpleDateFormat getDdMMyyyyFormat() {
        return ddMMyyyyFormat;
    }

    public static void setDdMMyyyyFormat(SimpleDateFormat ddMMyyyyFormat) {
        DateUtilities.ddMMyyyyFormat = ddMMyyyyFormat;
    }

    public static SimpleDateFormat getDdMMyySlashFormat() {
        return ddMMyySlashFormat;
    }

    public static void setDdMMyySlashFormat(SimpleDateFormat ddMMyySlashFormat) {
        DateUtilities.ddMMyySlashFormat = ddMMyySlashFormat;
    }

    public static SimpleDateFormat getDdMMyyyySlashFormat() {
        return ddMMyyyySlashFormat;
    }

    public static void setDdMMyyyySlashFormat(SimpleDateFormat ddMMyyyySlashFormat) {
        DateUtilities.ddMMyyyySlashFormat = ddMMyyyySlashFormat;
    }

    public static SimpleDateFormat getDdMMyyPointFormat() {
        return ddMMyyPointFormat;
    }

    public static void setDdMMyyPointFormat(SimpleDateFormat ddMMyyPointFormat) {
        DateUtilities.ddMMyyPointFormat = ddMMyyPointFormat;
    }

    public static SimpleDateFormat getDdMMyyyyPointFormat() {
        return ddMMyyyyPointFormat;
    }

    public static void setDdMMyyyyPointFormat(SimpleDateFormat ddMMyyyyPointFormat) {
        DateUtilities.ddMMyyyyPointFormat = ddMMyyyyPointFormat;
    }

}
