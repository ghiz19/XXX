import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Pm } from 'app/shared/model/pm.model';
import { PmService } from './pm.service';
import { PmComponent } from './pm.component';
import { PmDetailComponent } from './pm-detail.component';
import { PmUpdateComponent } from './pm-update.component';
import { PmDeletePopupComponent } from './pm-delete-dialog.component';
import { IPm } from 'app/shared/model/pm.model';

@Injectable({ providedIn: 'root' })
export class PmResolve implements Resolve<IPm> {
    constructor(private service: PmService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPm> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Pm>) => response.ok),
                map((pm: HttpResponse<Pm>) => pm.body)
            );
        }
        return of(new Pm());
    }
}

export const pmRoute: Routes = [
    {
        path: '',
        component: PmComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Pms'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PmDetailComponent,
        resolve: {
            pm: PmResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pms'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PmUpdateComponent,
        resolve: {
            pm: PmResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pms'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PmUpdateComponent,
        resolve: {
            pm: PmResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pms'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pmPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PmDeletePopupComponent,
        resolve: {
            pm: PmResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pms'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
