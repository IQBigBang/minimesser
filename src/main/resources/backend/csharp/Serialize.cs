using System;

public interface Serialize
{
    public void SerializeInto(Span<byte> mem);
}