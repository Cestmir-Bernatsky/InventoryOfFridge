package ReadFromFile;


import com.example.fridge.controllers.FridgeService;
import com.example.fridge.controllers.ManufacturersService;
import com.example.fridge.controllers.ProductsService;
import com.example.fridge.jpa.entity.FridgeEntity;
import com.example.fridge.jpa.entity.ManufacturersEntity;
import com.example.fridge.jpa.entity.ProductsEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ReadFromFile {

    public static FridgeEntity entityFromBarcode(String barcode, int pcs, ProductsService pService, ManufacturersService mService, FridgeService fService) {
        FridgeEntity entity = null;
        entity = fService.getBarcode(barcode);
        if(entity != null){
            entity.setPcs(entity.getPcs()+pcs);
            return entity;
        }
        entity = new FridgeEntity();
        Pattern pattern = Pattern.compile("(?<category>\\d{2})(?<product>\\d{2})(?<manufacturer>\\d{2})(?<year>\\d{4})(?<month>\\d{2})(?<day>\\d{2})");
        Matcher matcher = pattern.matcher(barcode);

        if(matcher.find()){
            System.out.println("Category of product = " + matcher.group("category"));
            System.out.println("Product id = " + matcher.group("product"));
            System.out.println("Manufacturer of product = " + matcher.group("manufacturer"));
            System.out.println("Expiration date = " + matcher.group("year" ) + "-" + matcher.group("month" )+ "-" + matcher.group("day" ) + "\n");
            ProductsEntity pEntity = pService.findByIdproductAndCategoryfkIdcategory(Integer.valueOf(matcher.group("product")), Integer.valueOf(matcher.group("category")));
            if(pEntity == null){
                System.out.println("Product is NULL");
                return null;
            }
            ManufacturersEntity mEntity = mService.getManufacturerById(Integer.valueOf(matcher.group("manufacturer")));
            if(mEntity == null){
                System.out.println("Manu is NULL");
                return null;
            }
            entity.setProductfk(pEntity);
            entity.setManufacturerfk(mEntity);
            entity.setExpiration(LocalDate.of(Integer.valueOf(matcher.group("year")),Integer.valueOf(matcher.group("month")), Integer.valueOf(matcher.group("day"))));
            entity.setPcs(pcs);
            entity.setAdded(LocalDate.now());
            entity.setBarcode(barcode);

        }

        System.out.println("Entity created");
        return entity;
    }

    public static Map<String, Integer> parseFile(MultipartFile file) {
        BufferedReader br;
        Map<String, Integer> result = new HashMap<>();
        try {
            String line;
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                if(result.containsKey(line)){
                    result.put(line, result.get(line).intValue()+1);
                }else {
                    result.put(line, 1);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
}
