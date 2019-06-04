import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Servicee } from 'app/shared/model/servicee.model';
import { ServiceeService } from './servicee.service';
import { ServiceeComponent } from './servicee.component';
import { ServiceeDetailComponent } from './servicee-detail.component';
import { ServiceeUpdateComponent } from './servicee-update.component';
import { ServiceeDeletePopupComponent } from './servicee-delete-dialog.component';
import { IServicee } from 'app/shared/model/servicee.model';

@Injectable({ providedIn: 'root' })
export class ServiceeResolve implements Resolve<IServicee> {
    constructor(private service: ServiceeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IServicee> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Servicee>) => response.ok),
                map((servicee: HttpResponse<Servicee>) => servicee.body)
            );
        }
        return of(new Servicee());
    }
}

export const serviceeRoute: Routes = [
    {
        path: '',
        component: ServiceeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Servicees'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ServiceeDetailComponent,
        resolve: {
            servicee: ServiceeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Servicees'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ServiceeUpdateComponent,
        resolve: {
            servicee: ServiceeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Servicees'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ServiceeUpdateComponent,
        resolve: {
            servicee: ServiceeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Servicees'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const serviceePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ServiceeDeletePopupComponent,
        resolve: {
            servicee: ServiceeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Servicees'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
