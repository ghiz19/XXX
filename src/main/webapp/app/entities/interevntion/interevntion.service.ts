import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInterevntion } from 'app/shared/model/interevntion.model';

type EntityResponseType = HttpResponse<IInterevntion>;
type EntityArrayResponseType = HttpResponse<IInterevntion[]>;

@Injectable({ providedIn: 'root' })
export class InterevntionService {
    public resourceUrl = SERVER_API_URL + 'api/interevntions';

    constructor(protected http: HttpClient) {}

    create(interevntion: IInterevntion): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(interevntion);
        return this.http
            .post<IInterevntion>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(interevntion: IInterevntion): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(interevntion);
        return this.http
            .put<IInterevntion>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IInterevntion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IInterevntion[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(interevntion: IInterevntion): IInterevntion {
        const copy: IInterevntion = Object.assign({}, interevntion, {
            datedebutintervention:
                interevntion.datedebutintervention != null && interevntion.datedebutintervention.isValid()
                    ? interevntion.datedebutintervention.toJSON()
                    : null,
            datefinintervention:
                interevntion.datefinintervention != null && interevntion.datefinintervention.isValid()
                    ? interevntion.datefinintervention.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.datedebutintervention = res.body.datedebutintervention != null ? moment(res.body.datedebutintervention) : null;
            res.body.datefinintervention = res.body.datefinintervention != null ? moment(res.body.datefinintervention) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((interevntion: IInterevntion) => {
                interevntion.datedebutintervention =
                    interevntion.datedebutintervention != null ? moment(interevntion.datedebutintervention) : null;
                interevntion.datefinintervention =
                    interevntion.datefinintervention != null ? moment(interevntion.datefinintervention) : null;
            });
        }
        return res;
    }
}
