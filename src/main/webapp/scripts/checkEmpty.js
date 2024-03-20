(function() {

	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);

	const error = urlParams.get("error")
	
	const input = document.getElementById("title")
	const submit = document.getElementById("submit")

	if (error) {
		alert("Title input are empty! FILL IT!")
		input.focus();
	}
	
	submit.addEventListener("click", (e) => {
		if (input.value.trim() == "") {
			e.preventDefault();
			alert("Title input are empty! FILL IT!")
			input.focus();
		}
	})
})();
