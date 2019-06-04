import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Planprevetinf } from 'app/shared/model/planprevetinf.model';
import { PlanprevetinfService } from './planprevetinf.service';
import { PlanprevetinfComponent } from './planprevetinf.component';
import { PlanprevetinfDetailComponent } from './planprevetinf-detail.component';
import { PlanprevetinfUpdateComponent } from './planprevetinf-update.component';
import { PlanprevetinfDeletePopupComponent } from './planprevetinf-delete-dialog.component';
import { IPlanprevetinf } from 'app/shared/model/planprevetinf.model';

@Injectable({ providedIn: 'root' })
export class PlanprevetinfResolve implements Resolve<IPlanprevetinf> {
    constructor(private service: PlanprevetinfService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPlanprevetinf> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Planprevetinf>) => response.ok),
                map((planprevetinf: HttpResponse<Planprevetinf>) => planprevetinf.body)
            );
        }
        return of(new Planprevetinf());
    }
}

export const planprevetinfRoute: Routes = [
    {
        path: '',
        component: PlanprevetinfComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Planprevetinfs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PlanprevetinfDetailComponent,
        resolve: {
            planprevetinf: PlanprevetinfResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Planprevetinfs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PlanprevetinfUpdateComponent,
        resolve: {
            planprevetinf: PlanprevetinfResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Planprevetinfs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PlanprevetinfUpdateComponent,
        resolve: {
            planprevetinf: PlanprevetinfResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Planprevetinfs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planprevetinfPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PlanprevetinfDeletePopupComponent,
        resolve: {
            planprevetinf: PlanprevetinfResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Planprevetinfs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
