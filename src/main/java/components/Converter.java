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
    public String convertToMD5ByMD(String word)  {
        validateValue(word);
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(word.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toLowerCase(Locale.ROOT);
        }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public String convertToMD5ByGuava(String word)  {
        validateValue(word);
        HashFunction hashFunction = Hashing.md5();
        HashCode hash = hashFunction.hashString(word, StandardCharsets.UTF_8);
        return hash.toString();
    }

    private void validateValue(String word) {
        if(word == null){
            throw new IllegalArgumentException("Value can't be null.");
        }
    }
}
