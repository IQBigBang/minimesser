import java.nio.ByteBuffer;

public interface ISerialize {
    void serializeInto(ByteBuffer _buf);
}