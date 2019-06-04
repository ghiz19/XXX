import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHistoriquetache } from 'app/shared/model/historiquetache.model';

type EntityResponseType = HttpResponse<IHistoriquetache>;
type EntityArrayResponseType = HttpResponse<IHistoriquetache[]>;

@Injectable({ providedIn: 'root' })
export class HistoriquetacheService {
    public resourceUrl = SERVER_API_URL + 'api/historiquetaches';

    constructor(protected http: HttpClient) {}

    create(historiquetache: IHistoriquetache): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(historiquetache);
        return this.http
            .post<IHistoriquetache>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(historiquetache: IHistoriquetache): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(historiquetache);
        return this.http
            .put<IHistoriquetache>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IHistoriquetache>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IHistoriquetache[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(historiquetache: IHistoriquetache): IHistoriquetache {
        const copy: IHistoriquetache = Object.assign({}, historiquetache, {
            datetimedebut:
                historiquetache.datetimedebut != null && historiquetache.datetimedebut.isValid()
                    ? historiquetache.datetimedebut.toJSON()
                    : null,
            detetimedebut:
                historiquetache.detetimedebut != null && historiquetache.detetimedebut.isValid()
                    ? historiquetache.detetimedebut.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.datetimedebut = res.body.datetimedebut != null ? moment(res.body.datetimedebut) : null;
            res.body.detetimedebut = res.body.detetimedebut != null ? moment(res.body.detetimedebut) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((historiquetache: IHistoriquetache) => {
                historiquetache.datetimedebut = historiquetache.datetimedebut != null ? moment(historiquetache.datetimedebut) : null;
                historiquetache.detetimedebut = historiquetache.detetimedebut != null ? moment(historiquetache.detetimedebut) : null;
            });
        }
        return res;
    }
}
