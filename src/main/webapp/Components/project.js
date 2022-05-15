$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});



// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateProjectForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	$("#formProject").submit();
});


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	$("#hidProjectIDSave").val($(this).closest("tr").find('#hidProjectIDUpdate').val());
	$("#projectName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#projectDescription").val($(this).closest("tr").find('td:eq(1)').text());
	$("#projectStartdate").val($(this).closest("tr").find('td:eq(2)').text());
	$("#projectEnddate").val($(this).closest("tr").find('td:eq(3)').text());
	$("#projectBudget").val($(this).closest("tr").find('td:eq(4)').text());
	$("#projectPrice").val($(this).closest("tr").find('td:eq(5)').text());
	$("#projectSponserId").val($(this).closest("tr").find('td:eq(6)').text());
});

// Delete============================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "ProjectAPI",
		type : "DELETE",
		data : "projectId=" + $(this).data("projectId"),
		dataType : "text",
		complete : function(response, status) {
			onEmployerDeleteComplete(response.responseText, status);
		}
	});
});

// CLIENT-MODEL================================================================
function validateProjectForm() {
	// NAME
	if ($("#projectName").val().trim() == "") {
		return "Insert Name.";
	}
	// DESCRIPTION
	if ($("#projectDescription").val().trim() == "") {
		return "Insert Project Description.";
	}
	// START DATE-------------------------------
	if ($("#projectStartdate").val().trim() == "") {
		return "Insert Project Start Date.";
	}
	// END DATE-------------------------------
	if ($("#projectEnddate").val().trim() == "") {
		return "Insert Project End Date.";
	}
	// BUDGET-------------------------------
	if ($("#projectBudget").val().trim() == "") {
		return "Insert Project Budget.";
	}
	// PRICE-------------------------------
	if ($("#projectPrice").val().trim() == "") {
		return "Insert Project Price.";
	}
	// SPONSER ID-------------------------------
	if ($("#projectSponserId").val().trim() == "") {
		return "Insert Project Sponser Id.";
	}
	
	
	return true;
}

$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateProjectForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#hidProjectIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			url: "ProjectAPI",
			type: type,
			data: $("#formProject").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onProjectSaveComplete(response.responseText, status);
			}
		});
});


function onProjectSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divProjectGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidProjectIDSave").val("");
	$("#formProject")[0].reset();
}



function onProjectDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divProjectGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

function onProjectDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divProjectGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

function onProjectDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divProjectGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}