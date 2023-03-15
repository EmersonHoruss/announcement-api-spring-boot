package com.escuelait.demo2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
	private Map<Integer, Announcement> announcements = new ConcurrentHashMap<>();
	private AtomicInteger id = new AtomicInteger();
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Announcement create(@RequestBody Announcement announcement) {
		int announcementId = id.getAndIncrement();  
		
		announcement.setId(announcementId);
		
		announcements.put(announcementId, announcement);
		
		return announcement;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Map<Integer, Announcement> read(Model model, Announcement announcement) {
		return announcements;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Announcement> readOne(@PathVariable Integer id) {	
		Announcement announcement = announcements.get(id);
		
		if(announcement != null) {
			return new ResponseEntity<>(announcement, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Announcement> updateOne(
			@PathVariable Integer id, @RequestBody Announcement updatedAnnouncement) {	
		Announcement announcement = announcements.get(id);
		
		if(announcement != null) {
			updatedAnnouncement.setId(id);
			announcements.put(id, updatedAnnouncement);
			return new ResponseEntity<>(updatedAnnouncement, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Announcement> deleteOne(@PathVariable Integer id) {	
		Announcement announcement = announcements.get(id);
		
		if(announcement != null) {
			announcements.remove(id);
			return new ResponseEntity<>(announcement, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
