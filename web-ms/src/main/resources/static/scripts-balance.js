$(document).ready(function () {
    $('#balance-table').DataTable({
        'aoColumnDefs': [{
            'bSortable': false,
            'aTargets': [-1]
        }]
    });
});

function getBalanceAmount() {
    if (Date.parse($('#from-date').val()) < Date.parse($('#check-date').val())) {
        $.ajax({
            type: 'GET',
            url: '/balance-list/betweenDates',
            data: {
                fromDate: $('#from-date').val(),
                checkDate: $('#check-date').val()
            }
        }).then(function (result) {

            console.log('result = ' + result);
            $('#balance-amount').text(" " + result + " руб.");
        });
    }
}


