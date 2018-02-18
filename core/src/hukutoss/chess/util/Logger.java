package hukutoss.chess.util;

public class Logger {

    //TODO: Probably need to do string format version of all methods.

    public static final int NONE = 0;
    public static final int ERROR = 1;
    public static final int INFO = 2;
    public static final int DEBUG = 3;

    private String tag;
    private int logLevel;

    public static Logger getLogger(Class clz) {
        return new Logger(clz, Logger.INFO);
    }

    public static Logger getLogger(Class clz, int level) {
        return new Logger(clz, level);
    }

    private Logger(Class clz, int level) {
        this.tag = clz.getName();
        this.logLevel = level;
    }

    public void debug(String message) {
        if (this.logLevel >= Logger.DEBUG) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), message);
        }
    }

    public void debug(String message, Throwable exception) {
        if (this.logLevel >= Logger.DEBUG) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), message);
            exception.printStackTrace(System.out);
        }
    }

    public void debug(String format, Object... args) {
        if (this.logLevel >= Logger.DEBUG) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), String.format(format, args));
        }
    }

    public void error(String message) {
        if (this.logLevel >= Logger.ERROR) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), message);
        }
    }

    public void error(String message, Throwable exception) {
        if (this.logLevel >= Logger.ERROR) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), message);
            exception.printStackTrace(System.out);
        }
    }

    public void error(String format, Object... args) {
        if (this.logLevel >= Logger.ERROR) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), String.format(format, args));
        }
    }

    public void info(String message) {
        if (this.logLevel >= Logger.INFO) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), message);
        }
    }

    public void info(String message, Throwable exception) {
        if (this.logLevel >= Logger.INFO) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), message);
            exception.printStackTrace(System.out);
        }
    }

    public void info(String format, Object... args) {
        if (this.logLevel >= Logger.INFO) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), String.format(format, args));
        }
    }

    public void log(String message) {
        if (this.logLevel >= Logger.NONE) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), message);
        }
    }

    public void log(int level, String message) {
        if (this.logLevel >= level) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), message);
        }
    }

    public void log(String message, Throwable exception) {
        if (this.logLevel >= Logger.NONE) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), message);
            exception.printStackTrace(System.out);
        }
    }

    public void log(int level, String message, Throwable exception) {
        if (this.logLevel >= level) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), message);
            exception.printStackTrace(System.out);
        }
    }

    public void log(String format, Object... args) {
        if (this.logLevel >= Logger.NONE) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), String.format(format, args));
        }
    }

    public void log(int level, String format, Object... args) {
        if (this.logLevel >= level) {
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            System.out.printf("%s:%s > %s %n", tag, Integer.toString(lineNumber), String.format(format, args));
        }
    }
}
