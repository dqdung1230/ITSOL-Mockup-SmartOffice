import {Component, OnInit} from '@angular/core';
import {User} from '../../../_models/user.model';
import {SearchEmployeeModel} from '../../../_models/request/search-employee.model';
import {Employee} from '../../../_models/employee.model';
import {EmployeeService} from '../../../_services/employee.service';

@Component({
    selector: 'app-employee-mng',
    templateUrl: './employee-mng.component.html',
    styleUrls: ['./employee-mng.component.css'],
    providers: [EmployeeService]
})
export class EmployeeMngComponent implements OnInit {

    employees: Employee[] = [];
    searchRequest: SearchEmployeeModel = new SearchEmployeeModel();
    insertRequest: SearchEmployeeModel = new SearchEmployeeModel();
    pageOptions: any = {
        page: 0,
        pageSize: 5,
        totalRows: 0,
        totalPages: 0
    };

    constructor(private employeeService: EmployeeService) {
    }

    ngOnInit(): void {
        this.searchRequest.page = this.pageOptions.page;
        this.searchRequest.pageSize = this.pageOptions.pageSize;
        this.doSearch();
    }

    onPageChanged(event) {
        console.log('-----', event);
        this.pageOptions.page = event.page - 1;
        this.pageOptions.pageSize = event.itemsPerPage;
        this.doSearch();
    }

    doSearch() {
        this.searchRequest.page = this.pageOptions.page;
        this.searchRequest.pageSize = this.pageOptions.pageSize;
        console.log('------- Search:', this.searchRequest);
        this.employeeService.post('/find-by-params', this.searchRequest).subscribe(data => {
            if (data.code === '00') {
                this.employees = data.datas;
                this.pageOptions.totalPages = data.totalPages;
                this.pageOptions.totalRows = data.totalRows;

            }
        });
    }

    doDelete(id) {
        this.employeeService.delete('/employee-delete?id=' + id).subscribe(data => {
            this.doSearch();
            alert('Xoa thanh cong');
            console.log(data);
        });
    }

    doInsert() {

    }
}
