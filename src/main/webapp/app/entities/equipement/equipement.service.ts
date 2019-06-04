import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEquipement } from 'app/shared/model/equipement.model';

type EntityResponseType = HttpResponse<IEquipement>;
type EntityArrayResponseType = HttpResponse<IEquipement[]>;

@Injectable({ providedIn: 'root' })
export class EquipementService {
    public resourceUrl = SERVER_API_URL + 'api/equipements';

    constructor(protected http: HttpClient) {}

    create(equipement: IEquipement): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(equipement);
        return this.http
            .post<IEquipement>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(equipement: IEquipement): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(equipement);
        return this.http
            .put<IEquipement>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEquipement>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEquipement[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(equipement: IEquipement): IEquipement {
        const copy: IEquipement = Object.assign({}, equipement, {
            dateAchat: equipement.dateAchat != null && equipement.dateAchat.isValid() ? equipement.dateAchat.format(DATE_FORMAT) : null,
            datedernieremaintenance:
                equipement.datedernieremaintenance != null && equipement.datedernieremaintenance.isValid()
                    ? equipement.datedernieremaintenance.format(DATE_FORMAT)
                    : null,
            dateexperation:
                equipement.dateexperation != null && equipement.dateexperation.isValid()
                    ? equipement.dateexperation.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateAchat = res.body.dateAchat != null ? moment(res.body.dateAchat) : null;
            res.body.datedernieremaintenance = res.body.datedernieremaintenance != null ? moment(res.body.datedernieremaintenance) : null;
            res.body.dateexperation = res.body.dateexperation != null ? moment(res.body.dateexperation) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((equipement: IEquipement) => {
                equipement.dateAchat = equipement.dateAchat != null ? moment(equipement.dateAchat) : null;
                equipement.datedernieremaintenance =
                    equipement.datedernieremaintenance != null ? moment(equipement.datedernieremaintenance) : null;
                equipement.dateexperation = equipement.dateexperation != null ? moment(equipement.dateexperation) : null;
            });
        }
        return res;
    }
}
