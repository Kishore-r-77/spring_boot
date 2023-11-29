package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.Address;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.AddressRepository;
@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<Address> getall(){
		return addressRepo.getallActive();
	}
	
	public Address getById(Long id) {
		return addressRepo.getActiveById(id);
	}
	
	public String add(Address entity) {
		entity.setValidFlag(1);
		addressRepo.save(entity);
		return errorService.getErrorById("ER001");
		
	}
	
	public String update(Long id, Address entity) {
		Address address = addressRepo.getActiveById(id);
		if(entity.getAddressType()!=null) {
			address.setAddressType(entity.getAddressType());
		}
		if(entity.getAddress1()!=null) {
			address.setAddress1(entity.getAddress1());
		}
		if(entity.getAddress2()!=null) {
			address.setAddress2(entity.getAddress2());
		}
		if(entity.getAddress3()!=null) {
			address.setAddress3(entity.getAddress3());
		}
		if(entity.getAddress4()!=null) {
			address.setAddress4(entity.getAddress4());
		}
		if(entity.getAddress5()!=null) {
			address.setAddress5(entity.getAddress5());
		}
		if(entity.getPostalCode()!=null) {
			address.setPostalCode(entity.getPostalCode());
		}
		if(entity.getState()!=null) {
			address.setState(entity.getState());
		}
		if(entity.getCountry()!=null) {
			address.setCountry(entity.getCountry());
		}
		if(entity.getMobile()!=null) {
			address.setMobile(entity.getMobile());
		}
		if(entity.getDistrict()!=null) {
			address.setDistrict(entity.getDistrict());
		}
		
		addressRepo.save(address);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		Address address = addressRepo.getActiveById(id);
		address.setValidFlag(-1);
		addressRepo.save(address);
		return errorService.getErrorById("ER008");
	}
	
	public String hardDelete(Long id) {
		addressRepo.deleteById(id);
		return errorService.getErrorById("ER008");
	}
	
	public List<Address> search(String key){
		return addressRepo.globalSearch(key);
	}

}
