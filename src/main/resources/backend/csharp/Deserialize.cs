using System;

public interface Deserialize
{
    public void DeserializeFrom(ReadOnlySpan<byte> mem);
}