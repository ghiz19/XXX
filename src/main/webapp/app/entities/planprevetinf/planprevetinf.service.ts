import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanprevetinf } from 'app/shared/model/planprevetinf.model';

type EntityResponseType = HttpResponse<IPlanprevetinf>;
type EntityArrayResponseType = HttpResponse<IPlanprevetinf[]>;

@Injectable({ providedIn: 'root' })
export class PlanprevetinfService {
    public resourceUrl = SERVER_API_URL + 'api/planprevetinfs';

    constructor(protected http: HttpClient) {}

    create(planprevetinf: IPlanprevetinf): Observable<EntityResponseType> {
        return this.http.post<IPlanprevetinf>(this.resourceUrl, planprevetinf, { observe: 'response' });
    }

    update(planprevetinf: IPlanprevetinf): Observable<EntityResponseType> {
        return this.http.put<IPlanprevetinf>(this.resourceUrl, planprevetinf, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlanprevetinf>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlanprevetinf[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
