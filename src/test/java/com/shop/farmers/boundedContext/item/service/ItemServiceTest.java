//package com.shop.farmers.boundedContext.item.service;
//
//import com.shop.farmers.boundedContext.item.constant.ItemSellStatus;
//import com.shop.farmers.boundedContext.item.dto.ItemFormDto;
//import com.shop.farmers.boundedContext.item.entity.Item;
//import com.shop.farmers.boundedContext.item.entity.ItemImg;
//import com.shop.farmers.boundedContext.item.repository.ItemImgRepository;
//import com.shop.farmers.boundedContext.item.repository.ItemRepository;
//import com.shop.farmers.boundedContext.item.service.ItemService;
//import jakarta.persistence.EntityNotFoundException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@Transactional
//@ActiveProfiles("test")
//public class ItemServiceTest {
//    @Autowired
//    ItemService itemService;
//
//    @Autowired
//    ItemRepository itemRepository;
//
//    @Autowired
//    ItemImgRepository itemImgRepository;
//
//    List<MultipartFile> createMultipartFiles() throws Exception {
//        List<MultipartFile> multipartFileList = new ArrayList<>();
//
//        for(int i=0;i<5;i++){
//            String path = "C:/shop/item"; // 테스트 파일 이미지가 생성되는 경로, 미리 생성되어있지 않으면 에러가 발생한다
//            String imageName = "image" + i + ".jpg";
//            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1, 2, 3, 4});
//            multipartFileList.add(multipartFile);
//        }
//
//        return multipartFileList;
//    }
//
//    @Test
//    @DisplayName("상품 등록 테스트")
//    @WithMockUser(username = "admin", roles = "ADMIN")
//    void saveItem() throws Exception {
//        ItemFormDto itemFormDto = new ItemFormDto();
//        itemFormDto.setItemNm("테스트상품");
//        itemFormDto.setItemSellStatus(ItemSellStatus.SELL);
//        itemFormDto.setItemDetail("테스트 상품입니다.");
//        itemFormDto.setPrice(1000);
//        itemFormDto.setStockNumber(100);
//
//        List<MultipartFile> multipartFileList = createMultipartFiles();
//        Long itemId = itemService.saveItem(itemFormDto, multipartFileList);
//
//        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
//        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
//
//        assertEquals(itemFormDto.getItemNm(), item.getItemNm());
//        assertEquals(itemFormDto.getItemSellStatus(), item.getItemSellStatus());
//        assertEquals(itemFormDto.getItemDetail(), item.getItemDetail());
//        assertEquals(itemFormDto.getPrice(), item.getPrice());
//        assertEquals(itemFormDto.getStockNumber(), item.getStockNumber());
//        assertEquals(multipartFileList.get(0).getOriginalFilename(), itemImgList.get(0).getOriImgName());
//    }
//}
