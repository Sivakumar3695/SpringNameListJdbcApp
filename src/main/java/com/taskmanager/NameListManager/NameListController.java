package com.taskmanager.NameListManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class NameListController
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/usersList")
	public List<NameEntity> getUserList() {

		String sql = "Select Name, Surname from NameList";
		return jdbcTemplate.query(sql, (rs, rowNum) -> new NameEntity(rs.getString("Name"), rs.getString("SurName")));
	}

	@GetMapping("/user")
	public List<NameEntity> getUserForGivenName(@RequestParam (value="name") String name) {

		String sql = "Select Name, Surname from NameList WHERE Name = ?";
		return jdbcTemplate.query(sql, new String[] {name} , (rs, rowNum) -> new NameEntity(rs.getString("Name"), rs.getString("SurName")));
	}

	@GetMapping("/user/{id}")
	public NameEntity getUserForGivenID(@PathVariable(value="id") String id) {

		String sql = "Select Name, Surname from NameList WHERE ID = ?";
		return jdbcTemplate.query(sql, new String[] {id} , (rs, rowNum) -> new NameEntity(rs.getString("Name"), rs.getString("SurName"))).get(0);
	}

	@PostMapping("/user/add")
	public @ResponseBody String addName(@RequestBody JsonNode jsonNode) {
		String sql = "INSERT INTO NameList (Name, Surname) VALUES (?, ?)";
		jdbcTemplate.update(sql, new Object[] {jsonNode.get("name").textValue(), jsonNode.get("surname").textValue()});
		return "Success";
	}

	@PutMapping("/user/{id}/update")
	public @ResponseBody String updateName(@RequestBody JsonNode jsonNode, @PathVariable(value = "id") String id) {
		try
		{
			String sql = "Update NameList SET Name=?, SurName=? WHERE ID=?";
			jdbcTemplate.update(sql, new Object[] {jsonNode.get("name").textValue(), jsonNode.get("surname").textValue(), id});
			return "Success";
		}
		catch(Exception e)
		{
			System.out.println(e);
			return "error";
		}
	}

	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteById(@PathVariable(value = "id") String id) {
		try
		{
			String sql = "Delete from NameList WHERE ID=?";
			jdbcTemplate.update(sql, new Object[] {id});
			return "Success";
		}
		catch(Exception e)
		{
			System.out.println(e);
			return "error";
		}
	}

	@DeleteMapping("/user")
	public @ResponseBody String deleteByName(@RequestParam(value = "name") String name) {
		try
		{
			String sql = "Delete from NameList WHERE Name=?";
			jdbcTemplate.update(sql, new Object[] {name});
			return "Success";
		}
		catch(Exception e)
		{
			System.out.println(e);
			return "error";
		}
	}
}
