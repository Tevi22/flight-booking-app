/**
 * Toggles the visibility of the return date field based on trip type selection.
 * Hides return date when 'One Way' is selected, and shows it when 'Round Trip' is selected.
 */
document.addEventListener("DOMContentLoaded", function() {
    const tripTypeSelect = document.getElementById("tripType");
    const returnDateGroup = document.getElementById("returnDateGroup");

    function toggleReturnDate() {
        const selected = tripTypeSelect.value;
        if (selected === "oneway") {
            returnDateGroup.style.display = "none";
            document.getElementById("returnDate").required = false;
        } else {
            returnDateGroup.style.display = "block";
            document.getElementById("returnDate").required = true;
        }
    }

    toggleReturnDate(); // Call on page load
    tripTypeSelect.addEventListener("change", toggleReturnDate); // Call on change
});