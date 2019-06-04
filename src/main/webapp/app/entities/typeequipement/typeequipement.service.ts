import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITypeequipement } from 'app/shared/model/typeequipement.model';

type EntityResponseType = HttpResponse<ITypeequipement>;
type EntityArrayResponseType = HttpResponse<ITypeequipement[]>;

@Injectable({ providedIn: 'root' })
export class TypeequipementService {
    public resourceUrl = SERVER_API_URL + 'api/typeequipements';

    constructor(protected http: HttpClient) {}

    create(typeequipement: ITypeequipement): Observable<EntityResponseType> {
        return this.http.post<ITypeequipement>(this.resourceUrl, typeequipement, { observe: 'response' });
    }

    update(typeequipement: ITypeequipement): Observable<EntityResponseType> {
        return this.http.put<ITypeequipement>(this.resourceUrl, typeequipement, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITypeequipement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITypeequipement[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
