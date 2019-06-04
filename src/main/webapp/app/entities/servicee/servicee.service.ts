import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IServicee } from 'app/shared/model/servicee.model';

type EntityResponseType = HttpResponse<IServicee>;
type EntityArrayResponseType = HttpResponse<IServicee[]>;

@Injectable({ providedIn: 'root' })
export class ServiceeService {
    public resourceUrl = SERVER_API_URL + 'api/servicees';

    constructor(protected http: HttpClient) {}

    create(servicee: IServicee): Observable<EntityResponseType> {
        return this.http.post<IServicee>(this.resourceUrl, servicee, { observe: 'response' });
    }

    update(servicee: IServicee): Observable<EntityResponseType> {
        return this.http.put<IServicee>(this.resourceUrl, servicee, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IServicee>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IServicee[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
