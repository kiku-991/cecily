package com.shoping.kiku.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shoping.kiku.object.FavoriteDto;
import com.shoping.kiku.object.ProductDto;
import com.shoping.kiku.object.StoreDto;
import com.shoping.kiku.service.FavoriteService;
import com.shoping.kiku.service.ProductService;
import com.shoping.kiku.service.StoreService;
import com.shoping.kiku.until.Session;
import com.shoping.kiku.until.Url;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	FavoriteService favoriteService;

	@Autowired
	StoreService storeService;

	/**
	 * 商品新規登録
	 * @param request
	 * @param product
	 * @return productmanger
	 */
	@RequestMapping(value = Url.CREATEPRODUCT, method = RequestMethod.POST)
	public String creProduct(@RequestParam("file") MultipartFile[] img, HttpServletRequest request,
			ProductDto product) {

		Session ss = (Session) request.getSession().getAttribute("userLogin");

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < img.length; i++) {
			MultipartFile file = img[i];
			// 保存文件
			list = saveFile(file, list);
		}
	
		productService.createProduct(ss.getUserId(), product,list);

		return "redirect:/center/myproducts";
	}

	/**
	 * 
	 * @param request
	 * @param file
	 * @param list
	 * @return
	 */
	private List<String> saveFile(MultipartFile file, List<String> list) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
				// )
				String filePath = Url.SAVEPATH + file.getOriginalFilename();
				list.add(Url.SRC+file.getOriginalFilename());
				File saveDir = new File(filePath);
				if (!saveDir.getParentFile().exists())
					saveDir.getParentFile().mkdirs();

				// 转存文件
				file.transferTo(saveDir);
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 商品IDによって商品編集
	 * @param id
	 * @param img
	 * @param pro
	 * @return
	 */
	@PostMapping(value = Url.UPDATEPROBYID)
	public String updateProById(@PathVariable("id") int id, @RequestParam("file") MultipartFile img, ProductDto pro) {
		String productImg = "";
		if (img.isEmpty() == false) {
			String proImg = img.getOriginalFilename();
			try {
				img.transferTo(
						new File(Url.SAVEPATH + proImg));
				System.out.println(img);
			} catch (Exception e) {
				e.printStackTrace();
			}

			productImg = Url.SRC + proImg;
		}
		productService.updateProduct(pro, id, productImg);
		return "redirect:/center/myproducts";
	}

	/**
	 * 商品IDによって商品削除
	 * @param pro
	 * @return productmanger
	 */
	@RequestMapping(value = Url.DELETEPROBYID)
	public String deleteProById(@PathVariable("id") int id) {
		productService.deletePro(id);
		return "redirect:/center/myproducts";
	}

	/**
	 * 商品IDによって商品を中止
	 * @param id
	 * @return productmanger
	 */
	@RequestMapping(value = Url.STOPSTATUSBYID)
	public String stopStatusById(@PathVariable("id") int id) {
		productService.stopStatus(id);
		return "redirect:/center/myproducts";

	}

	/**
	 * 商品IDによって中止商品を回復
	 * @param id
	 * @return productmanger
	 */
	@RequestMapping(value = Url.RECOVERYPROBYID)
	public String recoveryProById(@PathVariable("id") int id) {
		productService.recovery(id);
		return "redirect:/center/myproducts";
	}

	/**
	 * 商品詳細画面に遷移
	 * @return 商品詳細画面
	 */
	@RequestMapping(value = Url.PRODUCTDETAILS)
	public ModelAndView proDetails(@PathVariable("id") int id, HttpServletRequest request) {
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("productdetails");
		//登録あり
		if (ss != null) {
			//登録あり 商品と気に入り情報取得
			FavoriteDto fap = favoriteService.getProByUserIdAndProId(ss.getUserId(), id);
			mv.addObject("product", fap);
			int storeId = fap.getStoreId();
			StoreDto storeInfo = storeService.findByStoreId(storeId);
			mv.addObject("store", storeInfo);
		} else {
			//登録なし 商品情報取得
			ProductDto pros = productService.getProByProductId(id);
			mv.addObject("product", pros);
			int storeId = pros.getStoreId();
			StoreDto storeInfo = storeService.findByStoreId(storeId);
			mv.addObject("store", storeInfo);
		}
		return mv;
	}

	/**
	 * 商品IDによって商品編集(ADMIN)
	 * @param pro
	 * @return productmanger
	 */
	@PostMapping(value = Url.PROMANAGEREDIT)
	public String editProById(@PathVariable("id") int id, @RequestParam("file") MultipartFile img, ProductDto pro) {
		String productImg = "";
		if (img.isEmpty() == false) {
			String proImg = img.getOriginalFilename();
			try {
				img.transferTo(
						new File(Url.SAVEPATH + proImg));
				System.out.println(img);
			} catch (Exception e) {
				e.printStackTrace();
			}

			productImg = Url.SRC + proImg;
		}
		productService.updateProduct(pro, id, productImg);
		return "redirect:/center/productmanger";
	}

	/**
	 * 商品IDによって商品削除(ADMIN)
	 * @param pro
	 * @return productmanger
	 */
	@RequestMapping(value = Url.PROMANAGEREDELETE)
	public String deProById(@PathVariable("id") int id) {
		productService.deletePro(id);
		return "redirect:/center/productmanger";
	}

	/**
	 * 商品IDによって商品を中止(ADMIN)
	 * @param id
	 * @return productmanger
	 */
	@RequestMapping(value = Url.PROMANAGERSTOP)
	public String blockProById(@PathVariable("id") int id) {
		productService.stopStatus(id);
		return "redirect:/center/productmanger";

	}

	/**
	 * 商品IDによって中止商品を回復(ADMIN)
	 * @param id
	 * @return productmanger
	 */
	@RequestMapping(value = Url.PROMANAGERRECOVERY)
	public String reProById(@PathVariable("id") int id) {
		productService.recovery(id);
		return "redirect:/center/productmanger";
	}

}
