import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AccountDetails} from "../model/account.model";

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  backendHost: string = "http://localhost:8085"

  constructor(private http: HttpClient) {
  }

  public getAccount(accountId: string, page: number, size: number): Observable<AccountDetails> {
    return this.http.get<AccountDetails>(this.backendHost + "/accounts/" + accountId + "/pageOperations?page=" + page + "&size=" + size)
  }

  public debit(accountId: string, mount: number, description: string) {
    let data = {accountId: accountId, mount: mount, description: description}
    return this.http.post(this.backendHost + "/customers/debit", data);
  }

  public credit(accountId: string, mount: number, description: string) {
    let data = {accountId: accountId, mount: mount, description: description}
    return this.http.post(this.backendHost + "/customers/credit", data);
  }

  public transfer(accountSource: string, accountDestination: string, mount: number, description: string) {
    let data = {accountSource, accountDestination, mount, description}
    return this.http.post(this.backendHost + "/customers/transfer", data);
  }
}
