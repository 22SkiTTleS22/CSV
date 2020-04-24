import java.io.IOException;

class EmptyFileException extends IOException
{
    public EmptyFileException() {}

    public EmptyFileException(String message)
    {
        super(message);
    }
}