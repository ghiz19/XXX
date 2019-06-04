/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { ServiceeDetailComponent } from 'app/entities/servicee/servicee-detail.component';
import { Servicee } from 'app/shared/model/servicee.model';

describe('Component Tests', () => {
    describe('Servicee Management Detail Component', () => {
        let comp: ServiceeDetailComponent;
        let fixture: ComponentFixture<ServiceeDetailComponent>;
        const route = ({ data: of({ servicee: new Servicee(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [ServiceeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ServiceeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ServiceeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.servicee).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
