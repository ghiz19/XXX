import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Etat } from 'app/shared/model/etat.model';
import { EtatService } from './etat.service';
import { EtatComponent } from './etat.component';
import { EtatDetailComponent } from './etat-detail.component';
import { EtatUpdateComponent } from './etat-update.component';
import { EtatDeletePopupComponent } from './etat-delete-dialog.component';
import { IEtat } from 'app/shared/model/etat.model';

@Injectable({ providedIn: 'root' })
export class EtatResolve implements Resolve<IEtat> {
    constructor(private service: EtatService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEtat> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Etat>) => response.ok),
                map((etat: HttpResponse<Etat>) => etat.body)
            );
        }
        return of(new Etat());
    }
}

export const etatRoute: Routes = [
    {
        path: '',
        component: EtatComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Etats'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: EtatDetailComponent,
        resolve: {
            etat: EtatResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Etats'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: EtatUpdateComponent,
        resolve: {
            etat: EtatResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Etats'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: EtatUpdateComponent,
        resolve: {
            etat: EtatResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Etats'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const etatPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: EtatDeletePopupComponent,
        resolve: {
            etat: EtatResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Etats'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
