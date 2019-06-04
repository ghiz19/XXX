import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPm } from 'app/shared/model/pm.model';

type EntityResponseType = HttpResponse<IPm>;
type EntityArrayResponseType = HttpResponse<IPm[]>;

@Injectable({ providedIn: 'root' })
export class PmService {
    public resourceUrl = SERVER_API_URL + 'api/pms';

    constructor(protected http: HttpClient) {}

    create(pm: IPm): Observable<EntityResponseType> {
        return this.http.post<IPm>(this.resourceUrl, pm, { observe: 'response' });
    }

    update(pm: IPm): Observable<EntityResponseType> {
        return this.http.put<IPm>(this.resourceUrl, pm, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPm[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
