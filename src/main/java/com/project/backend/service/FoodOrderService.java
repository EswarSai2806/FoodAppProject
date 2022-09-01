package com.project.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.backend.dao.FoodOrderDao;
import com.project.backend.dao.ItemDao;
import com.project.backend.dao.StaffDao;
import com.project.backend.exception.FoodOrderNotFoundException;
import com.project.backend.model.FoodOrder;
import com.project.backend.model.Item;
import com.project.backend.model.Staff;
import com.project.backend.util.ResponseStructure;
@Service
public class FoodOrderService {
	@Autowired
	private FoodOrderDao dao;

	@Autowired
	private StaffDao staffDao;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private EmailSenderService emailSenderService;

	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodOrder foodOrder, List<Integer> itemIds,
			int staffId) {
		int totalPrice = 0;
		List<Item> items = new ArrayList<>();
		for (int itemId : itemIds) {
			Item item = itemDao.getItemById(itemId).get();
			totalPrice += item.getCost();
			items.add(item);
		}
		foodOrder.setItems(items);
		foodOrder.setTotalCost(totalPrice);

		Staff staff = staffDao.getStaffById(staffId).get();
		foodOrder.setStaff(staff);
		ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		structure.setMessage("Food Order Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveFoodOrder(foodOrder));
		String attachment = "C:\\Users\\U6070529\\Documents\\image.jpg";
		String body = "Hi " + structure.getT().getCustomerName() + ",\n\n"
				+ "Your order has been placed successfully. \n\n" + "Order Details :- \n\n";
		for (Item item : items) {
			body += "Item Name- " + item.getName() + ", Item Type- " + item.getType() + ", Item Price- "
					+ item.getCost() + "\n";
		}
		body += "Order Status- " + foodOrder.getStatus() + "\n" + "Total Amount- " + foodOrder.getTotalCost() + "\n\n"
				+ "If you need any support, please reach out to us on " + foodOrder.getStaff().getEmail() + " or "
				+ foodOrder.getStaff().getBranchManager().getEmail() + "." + "\n\n"
				+ "Thank you for choosing Food App, we are always there to fulfil your craving anytime!!!" + "\n\n"
				+ "Regards,\n" + "Team Food App";
		String subject = "Food Ordered Successfully";
		try {
			emailSenderService.sendMailWithAttachment(structure.getT().getCustomerEmail(), body, subject, attachment);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrderById(int id) {
		FoodOrder foodOrder = dao.getFoodOrderById(id).get();
		if (foodOrder == null) {
			throw new FoodOrderNotFoundException(id);
		} else {
			ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
			structure.setMessage("Food Order Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(foodOrder);
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(FoodOrder foodOrder, List<Integer> itemIds,
			int id) {
		ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		FoodOrder foodOrder2 = dao.updateFoodOrder(foodOrder, id);
		if (foodOrder2 != null) {
			int totalPrice = 0;
			List<Item> items = new ArrayList<>();
			for (int itemId : itemIds) {
				Item item = itemDao.getItemById(itemId).get();
				totalPrice += item.getCost();
				items.add(item);
			}
			foodOrder2.setItems(items);
			foodOrder2.setTotalCost(totalPrice);
			structure.setMessage("Item Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.saveFoodOrder(foodOrder2));
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("ID is not valid");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> deleteFoodOrder(int id) {
		ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		FoodOrder foodOrder = dao.getFoodOrderById(id).get();
		if (foodOrder != null) {
			structure.setMessage("Food Order Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.deleteFoodOrder(id));
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);
		} else {
			throw new FoodOrderNotFoundException(id);
		}
	}

	public ResponseEntity<ResponseStructure<List<FoodOrder>>> findAllFoodOrder() {
		ResponseStructure<List<FoodOrder>> structure = new ResponseStructure<List<FoodOrder>>();
		structure.setMessage("Food Order Found Successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllFoodOrder());
		return new ResponseEntity<ResponseStructure<List<FoodOrder>>>(structure, HttpStatus.OK);
	}
}
