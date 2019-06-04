import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILocalisations } from 'app/shared/model/localisations.model';

type EntityResponseType = HttpResponse<ILocalisations>;
type EntityArrayResponseType = HttpResponse<ILocalisations[]>;

@Injectable({ providedIn: 'root' })
export class LocalisationsService {
    public resourceUrl = SERVER_API_URL + 'api/localisations';

    constructor(protected http: HttpClient) {}

    create(localisations: ILocalisations): Observable<EntityResponseType> {
        return this.http.post<ILocalisations>(this.resourceUrl, localisations, { observe: 'response' });
    }

    update(localisations: ILocalisations): Observable<EntityResponseType> {
        return this.http.put<ILocalisations>(this.resourceUrl, localisations, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ILocalisations>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILocalisations[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
