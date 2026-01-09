/**
 * Sorts flight result table rows based on price (ascending or descending).
 */
function sortFlightsByPrice(order = 'asc') {
    const table = document.getElementById("flightsTable");
    const rows = Array.from(table.querySelectorAll("tbody tr"));

    const sortedRows = rows.sort((a, b) => {
        const priceA = parseFloat(a.querySelector(".price").textContent.replace(/[^0-9.]/g, ""));
        const priceB = parseFloat(b.querySelector(".price").textContent.replace(/[^0-9.]/g, ""));
        return order === 'asc' ? priceA - priceB : priceB - priceA;
    });

    const tbody = table.querySelector("tbody");
    tbody.innerHTML = "";
    sortedRows.forEach(row => tbody.appendChild(row));

    const sortLabel = document.getElementById("sortLabel");
    sortLabel.textContent = `Showing results sorted by: Price (${order === 'asc' ? 'Low to High' : 'High to Low'})`;
}

/**
 * Attach listener to sort dropdown
 */
document.addEventListener("DOMContentLoaded", function() {
    const sortDropdown = document.getElementById("sortBy");
    if (sortDropdown) {
        sortDropdown.addEventListener("change", function() {
            const selected = sortDropdown.value;
            if (selected === "priceAsc") {
                sortFlightsByPrice("asc");
            } else if (selected === "priceDesc") {
                sortFlightsByPrice("desc");
            }
        });
    }
});