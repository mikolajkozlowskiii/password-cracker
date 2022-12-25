package components;

import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@AllArgsConstructor
public class Converter {
    private String word;

    public String convertToMD5ByMD()  {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(word.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toLowerCase(Locale.ROOT);
            return myHash;
        }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public String convertToMD5ByGuava()  {
        HashFunction hashFunction = Hashing.md5();
        HashCode hash = hashFunction.hashString(word, StandardCharsets.UTF_8);
        return hash.toString();
    }
}
