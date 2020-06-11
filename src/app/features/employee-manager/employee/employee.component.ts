import {Component, OnInit, Pipe, PipeTransform} from '@angular/core';
import {Employee} from '../../../shared/model/employee/employee.model';
import {EmployeeService} from '../../../core/service/employee.service';
import {SearchEmployeeResponseModel} from '../../../shared/model/response/search-emp-response.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {environment} from '../../../../environments/environment';
import {Account} from '../../../shared/model/user/account.model';

@Component({
    selector: 'employee',
    templateUrl: './employee.component.html',
    providers: [EmployeeService],
})
export class EmployeeComponent implements OnInit {
    employee: Employee[] = [];
    accountRequest: Account = new Account();
    searchResponseModel: SearchEmployeeResponseModel = new SearchEmployeeResponseModel();
    pageOptions: any = {
        page: 0,
        pageSize: 5,
        totalRows: 0,
        totalPages: 0
    };
    allChecked = false;
    listIdChecked: any;
    fileToUpload: File = null;


    constructor(
        private employeeService: EmployeeService,
        private http: HttpClient,
        private route: ActivatedRoute,
        private router: Router
    ) {
    }

    ngOnInit(): void {
        // this.http.get(environment.api_url + 'user/search').subscribe((res: any) => {
        //     if (res.responseCode === 1) {
        //         this.employee = res.dataResponse;
        //         this.pageOptions.totalRows = 0;
        //         // this.projectIdSelected = this.listProject[0].id;
        //     }
        // });
        this.employeeService.search(this.accountRequest).subscribe((res: any) => {
            this.employee = res.data;


        });
        // this.doSearch();
    }

    doSearch() {
        this.searchResponseModel.page = this.pageOptions.page;
        this.searchResponseModel.pageSize = this.pageOptions.pageSize;
        // this.employeeService.search(this.accountRequest).subscribe(res => {
        //     this.accountRequest
        //     }
        // );
    }

    onPageChanged(event) {
        console.log('changedPage', this.searchResponseModel);
        this.pageOptions.page = event.page - 1;
        this.pageOptions.pageSize = event.itemsPerPage;
        this.doSearch();
    }


    checkAll() {
    }

    // doDelete() {
    //     const check: boolean = confirm('bạn có muốn xóa không?');
    //     const httpOptions = {
    //         headers: new HttpHeaders({'Content-Type': 'application/json'}),
    //         body: this.listIdChecked
    //     };
    //     this.http
    //         .delete('http://localhost:8082/api/delete', httpOptions)
    //         .subscribe((res: any) => {
    //             if (res.responseCode === 1) {
    //                 this.listIdChecked = [];
    //                 alert('xóa thành công');
    //                 window.location.reload();
    //             } else {
    //                 alert('xóa thất bại!');
    //             }
    //         });
    // }

    onSelectFile(event) { // called each time file input changes
        // if (event.target.files && event.target.files[0]) {
        //     const reader = new FileReader();
        //
        //     reader.readAsDataURL(event.target.files[0]); // read file as data url
        //     reader.onload = (event) => { // called once readAsDataURL is completed
        //         this.url = event.target.result;
        //     };
        // }
    }

    handleFileInput(files: FileList) {
        this.fileToUpload = files.item(0);
    }
    // uploadFileToActivity() {
    //     this.fileUploadService.postFile(this.fileToUpload).subscribe(data => {
    //         // do something, if upload success
    //     }, error => {
    //         console.log(error);
    //     });
    // }
}
