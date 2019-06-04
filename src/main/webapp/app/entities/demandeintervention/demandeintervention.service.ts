import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDemandeintervention } from 'app/shared/model/demandeintervention.model';

type EntityResponseType = HttpResponse<IDemandeintervention>;
type EntityArrayResponseType = HttpResponse<IDemandeintervention[]>;

@Injectable({ providedIn: 'root' })
export class DemandeinterventionService {
    public resourceUrl = SERVER_API_URL + 'api/demandeinterventions';

    constructor(protected http: HttpClient) {}

    create(demandeintervention: IDemandeintervention): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(demandeintervention);
        return this.http
            .post<IDemandeintervention>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(demandeintervention: IDemandeintervention): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(demandeintervention);
        return this.http
            .put<IDemandeintervention>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDemandeintervention>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDemandeintervention[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(demandeintervention: IDemandeintervention): IDemandeintervention {
        const copy: IDemandeintervention = Object.assign({}, demandeintervention, {
            datedemande:
                demandeintervention.datedemande != null && demandeintervention.datedemande.isValid()
                    ? demandeintervention.datedemande.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.datedemande = res.body.datedemande != null ? moment(res.body.datedemande) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((demandeintervention: IDemandeintervention) => {
                demandeintervention.datedemande = demandeintervention.datedemande != null ? moment(demandeintervention.datedemande) : null;
            });
        }
        return res;
    }
}
