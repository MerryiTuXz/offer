package throwable;

import java.io.FileNotFoundException;

public class ErrorAndException {
    private void throwError() {
        throw new StackOverflowError();
    }

    private void throwRuntimeException() {
        throw new RuntimeException();
    }

    private void throwCheckedExceptionA() {
        try {
            throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void throwCheckedExceptionB() throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    public static void main(String[] args) throws FileNotFoundException {
        ErrorAndException errorAndException = new ErrorAndException();
//        errorAndException.throwError();
        errorAndException.throwRuntimeException();
        errorAndException.throwCheckedExceptionA();
        errorAndException.throwCheckedExceptionB();
    }
}
