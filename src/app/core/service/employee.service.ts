import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Employee} from '../../shared/model/employee/employee.model';
import {environment} from '../../../environments/environment';
import {Observable, throwError} from 'rxjs';
import {Account} from '../../shared/model/user/account.model';
import {catchError} from 'rxjs/operators';

@Injectable({providedIn: 'root'})
export class EmployeeService {
    constructor(private  http: HttpClient) {
    }

    search(account: Account): Observable<Employee[]> {
        return this.http.post<Employee[]>(`${environment.api_url}user/search`, account).pipe(catchError(err => {
            return throwError(err);
        }));
    }

    getById(id: bigint) {
        return this.http.get<Employee>(`${environment.api_url}employee/get-info/${id}`).pipe(catchError(err => {
            return throwError(err);
        }));
    }
    // postFile(fileToUpload: File): Observable<boolean> {
    //     const endpoint = 'your-destination-url';
    //     const formData: FormData = new FormData();
    //     formData.append('fileKey', fileToUpload, fileToUpload.name);
    //     return this.httpClient
    //         .post(endpoint, formData, {headers: yourHeadersConfig})
    //         .map(() => {
    //             return true;
    //         })
    //         .catch((e) => this.handleError(e));
    // }
}
