$(document).ready(function () {
    $('#project-table').DataTable({
        'aoColumnDefs': [{
            'bSortable': false,
            'aTargets': [-1]
        }],
        drawCallback: () => {
            const table = $('#project-table').DataTable();
            const tableData = table.rows({ search: 'applied' }).data().toArray();
            const totals = tableData.reduce((total, rowData) => {
                total += parseFloat(rowData[2]);
                return total;
            }, 0);
            $('#total-amount').text("Итого: " + parseFloat(totals) + " рублей");
        }
    });
    searchSelectFunction('revenue-types', 'idRevenueTypes');

});


function dateSortFunction() {
    const minEl = document.querySelector('#from-date');
    const maxEl = document.querySelector('#to-date');
    const table = new DataTable('#project-table');
    table.search.fixed('range', function (searchStr, result, index) {
        var min = Date.parse(minEl.value);
        var max = Date.parse(maxEl.value);
        var columnDate = Date.parse(result[4]); // use data for the age column
        if (min <= columnDate
            && max >= columnDate) {
            return true; //
        }
        return false; //        
    });
    minEl.addEventListener('input', function () {
        table.draw();
    });
    maxEl.addEventListener('input', function () {
        table.draw();
    });
}


function searchSelectFunction(invitation, item) {
    $.ajax({
        type: 'GET',
        url: '/revenues-list/'+invitation,

    }).then(function (result) {
        var len = result.length;
        var dict = {};
        let list = document.getElementById(item);
        for (var i = 0; i < len; i++) {
            dict[result[i].id] = result[i].name;
            // console.log('result = ', result[i].name);
        }
        if (list.length == 0) {
            var val = document.getElementById(invitation).getAttribute('value');
            console.log('val == ', val);
            let firstOption = document.createElement('option');
            firstOption.value = "";
            firstOption.text = val;
            list.appendChild(firstOption);
            for (var key in dict) {
                let option_ = document.createElement("option");
                option_.value = key;
                option_.text = dict[key];
                list.appendChild(option_);
            }
        }
        console.log('------- ');
    });
    
}
function deleteItems(delItem) {
    const deleteElement = document.querySelector(delItem);
    deleteElement.innerHTML = '';
}

let table;

function sortBy(choice, column) {
    const pushObject = document.querySelector('#' + choice);
    const selectedValue = $('#' + choice + ' option:selected').text();
        console.log('in true = '+ (table == null)); 
        table = new DataTable('#project-table');
        table.search.fixed('range', function (searchStr, result, index) {
            var columnData = result[column];
            if (selectedValue == columnData) {
                return true; //
            }
            return false; //        
        });
    table.draw();

}

function showAllRecords() {
    const table = new DataTable('#project-table');
    table.search.fixed('range', function (searchStr, result, index) {
        return true;
    });
    table.draw();
}



function saveNewExpenseType() {
    
    $.ajax({
        type: 'POST',
        url: '/revenues-types/save-revenuetype',
        // dataType: 'json',
        data: new FormData($('#formRevenueType')),
        processData: false,
        contentType: false,
        success: function (response) {
            console.log(response);
        }
    });
}