import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Historiquetache } from 'app/shared/model/historiquetache.model';
import { HistoriquetacheService } from './historiquetache.service';
import { HistoriquetacheComponent } from './historiquetache.component';
import { HistoriquetacheDetailComponent } from './historiquetache-detail.component';
import { HistoriquetacheUpdateComponent } from './historiquetache-update.component';
import { HistoriquetacheDeletePopupComponent } from './historiquetache-delete-dialog.component';
import { IHistoriquetache } from 'app/shared/model/historiquetache.model';

@Injectable({ providedIn: 'root' })
export class HistoriquetacheResolve implements Resolve<IHistoriquetache> {
    constructor(private service: HistoriquetacheService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IHistoriquetache> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Historiquetache>) => response.ok),
                map((historiquetache: HttpResponse<Historiquetache>) => historiquetache.body)
            );
        }
        return of(new Historiquetache());
    }
}

export const historiquetacheRoute: Routes = [
    {
        path: '',
        component: HistoriquetacheComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Historiquetaches'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: HistoriquetacheDetailComponent,
        resolve: {
            historiquetache: HistoriquetacheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Historiquetaches'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: HistoriquetacheUpdateComponent,
        resolve: {
            historiquetache: HistoriquetacheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Historiquetaches'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: HistoriquetacheUpdateComponent,
        resolve: {
            historiquetache: HistoriquetacheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Historiquetaches'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const historiquetachePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: HistoriquetacheDeletePopupComponent,
        resolve: {
            historiquetache: HistoriquetacheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Historiquetaches'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
